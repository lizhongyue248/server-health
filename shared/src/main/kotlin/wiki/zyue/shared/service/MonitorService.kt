package wiki.zyue.shared.service

import oshi.SystemInfo
import oshi.hardware.*
import oshi.software.os.OSService
import oshi.software.os.OperatingSystem
import oshi.util.Util
import wiki.zyue.shared.modal.AgentInformation
import wiki.zyue.shared.modal.HardwareInformation
import wiki.zyue.shared.modal.NetworkInformation
import wiki.zyue.shared.modal.SystemInformation

fun interface MonitorService {
    /**
     * 统计系统监控信息
     */
    fun statistics(): AgentInformation
}

class MonitorServiceImpl : MonitorService {
    override fun statistics(): AgentInformation {
        val systemInfo = SystemInfo()
        val hardware: HardwareAbstractionLayer = systemInfo.hardware
        val operatingSystem = systemInfo.operatingSystem
        return AgentInformation(
            "agent",
            operatingSystem.isElevated,
            getHardwareInformation(operatingSystem, hardware),
            getNetworkInformation(operatingSystem, hardware),
            getSystemInformation(operatingSystem, hardware),
        )
    }

    private fun getSystemInformation(
        operatingSystem: OperatingSystem,
        hardware: HardwareAbstractionLayer
    ): SystemInformation {
        val memory: GlobalMemory = hardware.memory
        val services = operatingSystem.services
        return SystemInformation(
            operatingSystem.toString(),
            memory.toString(),
            memory.total,
            memory.available,
            memory.virtualMemory.swapTotal,
            memory.virtualMemory.swapUsed,
            operatingSystem.threadCount,
            operatingSystem.processCount,
            operatingSystem.systemBootTime,
            operatingSystem.systemUptime,
            operatingSystem.versionInfo.toString(),
            services.size,
            services.filter { it.state == OSService.State.RUNNING }.size
        )
    }

    private fun getNetworkInformation(
        operatingSystem: OperatingSystem,
        hardware: HardwareAbstractionLayer
    ): NetworkInformation {
        val networkIFs: List<NetworkIF> = hardware.networkIFs
        val internetProtocolStats = operatingSystem.internetProtocolStats
        val connections = internetProtocolStats.connections
        val tcPv4Stats = internetProtocolStats.tcPv4Stats
        val tcPv6Stats = internetProtocolStats.tcPv6Stats
        return NetworkInformation(
            tcPv4Stats.connectionsEstablished,
            tcPv6Stats.connectionsEstablished,
            connections.filter { it.type == "udp4" }.size.toLong(),
            connections.filter { it.type == "udp6" }.size.toLong(),
            networkIFs.sumOf { it.bytesRecv },
            networkIFs.sumOf { it.bytesSent },
            "region",
            "ip"
        )
    }

    private fun getHardwareInformation(
        operatingSystem: OperatingSystem,
        hardware: HardwareAbstractionLayer
    ): HardwareInformation {
        val processor = hardware.processor
        val diskStores: List<HWDiskStore> = hardware.diskStores
        return HardwareInformation(
            processor.processorIdentifier.name,
            getCpuUsage(processor),
            processor.physicalProcessorCount,
            processor.logicalProcessorCount,
            processor.maxFreq,
            diskStores.size,
            diskStores.sumOf { it.size },
            operatingSystem.fileSystem.fileStores.sumOf { it.freeSpace }
        )
    }

    private fun getCpuUsage(processor: CentralProcessor): Double {
        val loadTicks1 = processor.systemCpuLoadTicks
        Util.sleep(1000)
        val loadTicks2 = processor.systemCpuLoadTicks
        val totalCpu =
            CentralProcessor.TickType.entries.sumOf { loadTicks2[it.index] - loadTicks1[it.index] }.toDouble()
        val idleCpu =
            loadTicks2[CentralProcessor.TickType.IDLE.index] - loadTicks1[CentralProcessor.TickType.IDLE.index].toDouble()
        return (100.0 * (1.0 - idleCpu / totalCpu))
    }
}
package wiki.zyue.shared.modal

data class HardwareInformation(
    /**
     * cpu 型号
     */
    val cpu: String,
    /**
     * cpu 使用率
     */
    val cpuUsage: Double,
    /**
     * cpu 物理核心数
     */
    val cpuPhysicalProcessorCount: Int,
    /**
     * cpu 逻辑核心数
     */
    val cpuLogicalProcessorsCount: Int,
    /**
     * cpu 最大频率
     */
    val maxFreq: Long,
    /**
     * 硬盘数
     */
    val diskNumber: Int,
    /**
     * 硬盘总大小
     */
    val diskSize: Long,
    /**
     * 硬盘可用大小
     */
    val diskAvailable: Long,
) {
}
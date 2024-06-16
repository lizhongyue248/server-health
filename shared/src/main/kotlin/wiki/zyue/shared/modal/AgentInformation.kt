package wiki.zyue.shared.modal

data class AgentInformation(
    /**
     * agent 名称
     */
    val name: String,

    /**
     * 是否使用特权用户运行程序
     */
    val isElevated: Boolean,

    /**
     * 硬件信息
     */
    val hardware: HardwareInformation?,

    /**
     * 网络信息
     */
    val network: NetworkInformation?,

    /**
     * 系统信息
     */
    val system: SystemInformation?,
) {

}
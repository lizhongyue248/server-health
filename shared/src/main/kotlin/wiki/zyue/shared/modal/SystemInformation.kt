package wiki.zyue.shared.modal

data class SystemInformation(
    /**
     * 操作系统全称
     */
    val name: String,
    /**
     * 内存情况
     */
    val memory: String,
    /**
     * 可用内存
     */
    val availableMemory: Long,
    /**
     * 总内存
     */
    val totalMemory: Long,
    /**
     * 交换空间总内存
     */
    val swapTotal: Long,
    /**
     * 已经使用的交换空间内存
     */
    val swapUsed: Long,
    /**
     * 线程总数
     */
    val threadCount: Int,
    /**
     * 进程总数
     */
    val processCount: Int,
    /**
     * 系统启动时间
     */
    val bootTIme: Long,
    /**
     * 系统运行时间
     */
    val uptime: Long,
    /**
     * 系统版本
     */
    val versionInfo: String,
    /**
     * 服务总数
     */
    val serviceCount: Int,
    /**
     * 正在运行的服务总数
     */
    val serviceRunningCount: Int
) {
}
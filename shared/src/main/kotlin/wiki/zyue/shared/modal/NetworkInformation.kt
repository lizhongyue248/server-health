package wiki.zyue.shared.modal

data class NetworkInformation(
    /**
     * IPV4 tcp 连接数
     */
    val tcpV4Number: Long,
    /**
     * IPV6 tcp 连接数
     */
    val tcpV6Number: Long,
    /**
     * IPV4 udp 连接数
     */
    val udpV4Number: Long,
    /**
     * IPV6 udp 连接数
     */
    val udpV6Number: Long,
    /**
     * 下载流量
     */
    val downloadTraffic: Long,
    /**
     * 上传流量
     */
    val uploadTraffic: Long,
    /**
     * 所属区域
     */
    val region: String,
    /**
     * IP 地址
     */
    val ip: String,
)

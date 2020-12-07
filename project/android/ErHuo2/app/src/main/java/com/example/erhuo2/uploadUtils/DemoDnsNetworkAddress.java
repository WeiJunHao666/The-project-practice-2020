package com.example.erhuo2.uploadUtils;


import com.qiniu.android.http.dns.IDnsNetworkAddress;



public class DemoDnsNetworkAddress implements IDnsNetworkAddress {
    private final String hostValue;
    private final String ipValue;
    private final Long ttlValue;
    private final String sourceValue;
    private final Long timestampValue;
    DemoDnsNetworkAddress(String hostValue,
                          String ipValue,
                          Long ttlValue,
                          String sourceValue,
                          Long timestampValue) {
        this.hostValue = hostValue;
        this.ipValue = ipValue;
        this.ttlValue = ttlValue;
        this.sourceValue = sourceValue;
        this.timestampValue = timestampValue;
    }
    @Override
    public String getHostValue() {
        return hostValue;
    }
    @Override
    public String getIpValue() {
        return ipValue;
    }
    @Override
    public Long getTtlValue() {
        return ttlValue;
    }
    @Override
    public String getSourceValue() {
        return sourceValue;
    }
    @Override
    public Long getTimestampValue() {
        return timestampValue;
    }
//    public Dns buildDefaultDns() {
//        // 可添加修改 多个 IResolver
//        // 适当调整不同 IResolver 的加入顺序，比如:223.5.5.5，119.29.29.29，114.114.114.114，8.8.8.8 等
//        ArrayList<IResolver> rs = new ArrayList<IResolver>(3);
//        try {
//            IResolver r1 = new Resolver(InetAddress.getByName("119.29.29.29"));
//            rs.add(r1);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
////        try {
////            rs.add(new Resolver(InetAddress.getByName("114.114.114.114")));
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////        try {
////            // 读取系统相关属性
////            // android 27 及以上 会报错
////            IResolver r2 = AndroidDnsServer.defaultResolver();
////            rs.add(r2);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
//        if (rs.size() == 0) {
//            return null;
//        }
//        final DnsManager happlyDns = new DnsManager(NetworkInfo.normal, rs.toArray(new IResolver[rs.size()]));
//        Dns dns = new Dns() {
//            // 若抛出异常 Exception ，sdk 会使用 okhttp 组件默认 dns 解析结果
//            @Override
//            public List<IDnsNetworkAddress> lookup(String hostname) throws UnknownHostException {
//                Domain domain = new Domain(hostname);
//                List<IDnsNetworkAddress> addressList = null;
//                try {
//                    Record[] records = happlyDns.queryRecords(domain);
//                    if (records != null && records.length > 0){
//                        addressList = new ArrayList<>();
//                        for (Record record : records) {
//                            String source = "customized";
//                            DemoDnsNetworkAddress address = new DemoDnsNetworkAddress(hostname, record.value, (long)record.ttl, source, record.timeStamp);
//                            addressList.add(address);
//                        }
//                    }
//                } catch (IOException ignored) {
//                }
//                return addressList;
//            }
//        };
//        return dns;
//    }
}

[![Build Status](https://travis-ci.org/fengjiachun/Jupiter.svg?branch=master)](https://travis-ci.org/fengjiachun/Jupiter)

###Jupiter

    Jupiter是一个分布式服务框架

  -------------------------------------------------------------------------------------------------------

######Jupiter Architecture:

           ═ ═ ═▷ init         ─ ─ ─ ▷ async       ──────▶ sync
      ***********************************************************************************
                                ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
                                           ┌ ─ ─ ─ ┐ │
               ─ ─ ─ ─ ─ ─ ─ ─ ─│ Registry  Monitor ───────────────────────────┐
              │                            └ ─ ─ ─ ┘ │                         │
                                └ ─ ─△─ ─ ─ ─ ─△─ ─ ─                          ▼
              │                                                           ┌ ─ ─ ─ ─
            Notify                   ║         ║                            Telnet │
              │         ═ ═ ═ ═ ═ ═ ═           ═ ═ ═ ═ ═ ═ ═ ═ ═         └ ─ ─ ─ ─
                       ║                                         ║             ▲
              │    Subscribe                                  Register         │
                       ║                                         ║             │
              │  ┌ ─ ─ ─ ─ ─                          ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─    │
                            │─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ▷           ┌ ─ ─ ─ ┐ │   │
              └ ▷│ Consumer           Invoke          │ Provider  Monitor ─────┘
                            │────────────────────────▶           └ ─ ─ ─ ┘ │
                 └ ─ ─ ─ ─ ─                          └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
      ***********************************************************************************

  -------------------------------------------------------------------------------------------------------
- [传输层工作流程](https://github.com/fengjiachun/Jupiter/blob/master/doc/transport.md)
- [Option参数](https://github.com/fengjiachun/Jupiter/blob/master/jupiter-transport/src/main/java/org/jupiter/transport/JOption.java)

######性能报告(client, server各一台4核心机器):
    - 小数据包同步阻塞调用qps: 11w+
    - 小数据包Future方式调用qps: 17w+ (比同步阻塞调用qps高一些是因为阻塞调用导致瓶颈在客户端, 如果有多台机器作为客户端测试理论上也能达到这个值)
- [测试详细数据](https://github.com/fengjiachun/Jupiter/blob/master/doc/benchmark.md)
- [BenchmarkClient](https://github.com/fengjiachun/Jupiter/blob/master/jupiter-example/src/main/java/org/jupiter/benchmark/tcp/BenchmarkClient.java)
- [BenchmarkServer](https://github.com/fengjiachun/Jupiter/blob/master/jupiter-example/src/main/java/org/jupiter/benchmark/tcp/BenchmarkServer.java)

######一些特性:
- 支持同步阻塞调用, 异步Future, Callback方式调用, [异步链式调用](https://github.com/fengjiachun/Jupiter/blob/master/doc/promise.md)
- 支持单播和广播
- 支持泛化调用
- 支持udt(有点鸡肋)
- 序列化/反序列化: 基于SPI并可扩展, 默认使用protostuff
- 服务发布/订阅: 基于SPI并可扩展, 提供默认注册中心实现, 也支持zookeeper, 支持线上调整已有服务(通过flightexec)
- 软负载均衡: 基于SPI并可扩展, 默认实现是随机, 按照权重和预热时间设置随机概率
- 流量控制: 提供app级别和provider级别两种粒度流控, 支持线上调整(通过flightexec)
- 监控服务: 只能使用telnet, 有点鸡肋, 因为我不会做网页
- 性能指标度量: log, csv, telnet
- 链路跟踪: 链路最前端会生成全局唯一的traceId, 后边需要业务代码埋点, 和业务代码之间通过ThreadLocal透传traceId
- 调用超时设置支持两种粒度: provider级别和方法级别
- [线上调试(flightexec)](https://github.com/fengjiachun/Jupiter/blob/master/jupiter-flightexec/src/main/java/org/jupiter/flight/exec/package-info.java)


######版本变更:
    - v1.0 服务不能单独指定或切换序列化/反序列化的方式, 这不太合理
    - v1.1 为了同时支持多种序列化方式, 协议头发生变更, Sign(一个byte)的高地址4位用来标记序列化/反序列化方式, 低地址4位意义不变(暂时没有机器做性能测试和疲劳测试了, 相比v1.0可能不够稳定)

######Jupiter使用, 请参考示例代码(挺全的):
- [jupiter-example#org.jupiter.example.*](https://github.com/fengjiachun/Jupiter/tree/master/jupiter-example/src/main/java/org/jupiter/example)

######其他:
- 闲暇时间写着玩的, 娱乐性质, 不过如有人希望尝试使用, 我承诺会一直维护
     1. qq交流: 71287116
     2. 邮件交流: jiachun_fjc@163.com



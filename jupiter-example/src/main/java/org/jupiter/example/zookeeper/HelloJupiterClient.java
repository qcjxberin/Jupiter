/*
 * Copyright (c) 2015 The Jupiter Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jupiter.example.zookeeper;

import org.jupiter.example.ServiceTest;
import org.jupiter.rpc.consumer.ProxyFactory;
import org.jupiter.transport.JConnection;
import org.jupiter.transport.JConnector;
import org.jupiter.transport.exception.ConnectFailedException;
import org.jupiter.transport.netty.JNettyTcpConnector;

/**
 * RegistryServer是基于SPI的, 使用zookeeper的话maven引入jupiter-registry-zookeeper即可
 *
 * jupiter
 * org.jupiter.example.zookeeper
 *
 * @author jiachun.fjc
 */
public class HelloJupiterClient {

    public static void main(String[] args) {
        JConnector<JConnection> connector = new JNettyTcpConnector();
        // 连接RegistryServer
        connector.connectToRegistryServer("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
        // 自动管理可用连接
        JConnector.ConnectionManager manager = connector.manageConnections(ServiceTest.class);
        // 等待连接可用
        if (!manager.waitForAvailable(3000)) {
            throw new ConnectFailedException();
        }

        ServiceTest service = ProxyFactory.factory(ServiceTest.class)
                .connector(connector)
                .newProxyInstance();

        try {
            ServiceTest.ResultClass result = service.sayHello();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

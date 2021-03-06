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

package org.jupiter.example;

import org.jupiter.rpc.ServiceProvider;

import java.io.Serializable;
import java.util.List;

/**
 * jupiter
 * org.jupiter.example
 *
 * @author jiachun.fjc
 */
@ServiceProvider(group = "test", version = "1.0.0.daily")
public interface ServiceTest {

    ResultClass sayHello();

    class ResultClass implements Serializable {
        private static final long serialVersionUID = -6514302641628274984L;

        String str;
        int num;
        Long lon;
        List<String> list;

        @Override
        public String toString() {
            return "ResultClass{" +
                    "str='" + str + '\'' +
                    ", num=" + num +
                    ", lon=" + lon +
                    ", list=" + list +
                    '}';
        }
    }
}

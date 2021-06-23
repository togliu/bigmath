/*
 * Copyright 2021 Lars Tennstedt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ltennstedt.finnmath.linear.field

/**
 * Single implementation of the [Double] field
 *
 * @author Lars Tennstedt
 * @since 0.0.1
 */
public object DoubleField : Field<Double, Double> {
    override val addition: (a: Double, b: Double) -> Double
        get() = { a, b -> a + b }

    override val subtraction: (a: Double, b: Double) -> Double
        get() = { a, b -> a - b }

    override val multiplication: (a: Double, b: Double) -> Double
        get() = { a, b -> a * b }

    override val division: (a: Double, b: Double) -> Double
        get() = { a, b -> a / b }

    override val zero: Double
        get() = 0.0
}

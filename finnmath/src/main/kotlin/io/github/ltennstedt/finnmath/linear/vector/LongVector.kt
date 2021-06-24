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
package io.github.ltennstedt.finnmath.linear.vector

import com.google.common.annotations.Beta // ktlint-disable import-ordering
import kotlin.math.absoluteValue
import kotlin.math.sqrt
import org.apiguardian.api.API

@API(status = API.Status.EXPERIMENTAL, since = "0.0.1")
@Beta
public class LongVector(
    indexToElement: Map<Int, Long>
) : AbstractVector<Long, LongVector, Double, Long>(
    indexToElement
) {
    override fun add(summand: LongVector): LongVector {
        require(size == summand.size) { "Equal sizes expected but $size!=${summand.size}" }
        return LongVector(indexToElement.map { (i, e) -> i to (e + summand.element(i)) }.toMap())
    }

    override fun subtract(subtrahend: LongVector): LongVector {
        require(size == subtrahend.size) { "Equal sizes expected but $size!=${subtrahend.size}" }
        return LongVector(indexToElement.map { (i, e) -> i to (e + subtrahend.element(i)) }.toMap())
    }

    override fun dotProduct(other: LongVector): Long {
        require(size == other.size) { "Equal sizes expected but $size!=${other.size}" }
        return indexToElement.map { (i, e) -> e * other.element(i) }.reduce { a, b -> a + b }
    }

    override fun scalarMultiply(scalar: Long): LongVector = LongVector(
        indexToElement.map { (i, e) -> i to (scalar * e) }.toMap()
    )

    override fun negate(): LongVector = LongVector(indexToElement.map { (i, e) -> i to (-e) }.toMap())

    override fun orthogonalTo(other: LongVector): Boolean {
        require(size == other.size) { "Equal sizes expected but $size!=${other.size}" }
        return indexToElement.map { (i, e) -> e * other.element(i) }.reduce { a, b -> a + b } == 0L
    }

    override fun taxicabNorm(): Double = indexToElement.values.map { e -> e.absoluteValue }
        .reduce { a, b -> a + b }
        .toDouble()

    override fun euclideanNormPow2(): Long = dotProduct(this)

    override fun euclideanNorm(): Double = sqrt(euclideanNormPow2().toDouble())

    override fun maxNorm(): Double = indexToElement.values.map { it.absoluteValue.toDouble() }.maxOrNull() as Double

    override fun equalsByComparing(other: LongVector): Boolean {
        require(size == other.size) { "Equal sizes expected but $size!=${other.size}" }
        return indexToElement.all { (i, e) -> e.compareTo(other.element(i)) == 0 }
    }

    public companion object
}
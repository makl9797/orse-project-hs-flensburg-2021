package models

import dev.fritz2.identification.uniqueId
import dev.fritz2.lenses.IdProvider
import dev.fritz2.lenses.Lenses
import dev.fritz2.resource.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json


@Lenses
@Serializable
data class Customer(
    var _id: String = uniqueId(),
    val address: Address,
    val firstname: String,
    val lastname: String
)


object CustomerResource : Resource<Customer, String> {
    override val idProvider: IdProvider<Customer, String> = Customer::_id

    override fun deserialize(source: String): Customer = Json.decodeFromString(Customer.serializer(), source)
    override fun deserializeList(source: String): List<Customer> =
        Json.decodeFromString(ListSerializer(Customer.serializer()), source)

    override fun serialize(item: Customer): String = Json.encodeToString(Customer.serializer(), item)
    override fun serializeList(items: List<Customer>): String =
        Json.encodeToString(ListSerializer(Customer.serializer()), items)
}
package nti.team.entity

data class Product(
    val id: Int?,
    val category_id: Int?,
    val name:  String?,
    val description: String?,
    val image: String?,
    val price_current: Int?,
    val price_old: Double?,
    val measure: Double?,
    val measure_unit: String?,
    val energy_per_100_grams: Double?,
    val proteins_per_100_grams: Double?,
    val fats_per_100_grams: Double?,
    val carbohydrates_per_100_grams: Double?,
    val tag_ids: List<Int>
)

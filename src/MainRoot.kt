fun main() {
    val itemType = getRandomItemType()
    print(inspectItemTypeUgly(itemType))
    println("itemType=${ItemType::class.findConstantNameByValue(itemType)}")

    println("errorCode=" + ErrorCodes::class.findConstantNameByValue(0))

    println("sourceType=" + myConstantTopClass.getConstantNameByValues(0))

    println("is ItemType KotlinObject=${ItemType::class.isKotlinObject()}")
    println("is ErrorCode KotlinObject=${ErrorCodes::class.isKotlinObject()}")
    println("is Book KotlinObject=${Book::class.isKotlinObject()}")
}


private fun inspectItemTypeUgly(itemType: Int) {
    val type = when(itemType) {
        ItemType.TYPE_TEXT -> "text"
        ItemType.TYPE_AUDIO -> "audio"
        ItemType.TYPE_IMG -> "image"
        ItemType.TYPE_LINK -> "link"
        ItemType.TYPE_VIDEO -> "video"
        else -> null
    }
    println("inspect item type =$type;originalValue=$itemType")
}


private fun getRandomItemType(): Int {
    return  0
}
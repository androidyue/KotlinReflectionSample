import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticProperties
import kotlin.reflect.jvm.kotlinProperty

fun <T> KClass<*>.findConstantNameByValue(value: T): String? {
    return if (this.isKotlinObject()) {
        getConstantNameByValueForObject(this, value)
    } else {
        getConstantNameByValueFromNormalClass(this, value)
    }
}

fun <T> getConstantNameByValueFromNormalClass(kClass: KClass<*>, value: T): String? {
    value ?: return null
    return kClass.staticProperties.filter {
        it.isFinal
    }.firstOrNull() {
        it.getter.call() == value
    }?.name
}

fun <T> getConstantNameByValueForObject(kClass: KClass<*>, value: T): String? {
    value ?: return null
    return kClass.memberProperties.filter {
        it.isFinal
    }.firstOrNull {
        it.getter.call() == value
    }?.name
}

fun <T> Class<*>.getConstantNameByValues(value: T): String? {
    value ?: return null
    return declaredFields.mapNotNull {
        it.kotlinProperty
    }.filter {
        it.isFinal
    }.firstOrNull {
        it.getter.call() == value
    }?.name
}

fun KClass<*>.isKotlinObject(): Boolean {
    return this.objectInstance != null
}


package com.project.compose.core.navigation.helper

import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Extension function to create a composable screen in the NavGraphBuilder.
 * @param useDefaultTransition Boolean flag to use default transition
 * @param customArgs Map of custom arguments
 * @param deepLinks List of deep links
 * @param content Composable content to display
 */
inline fun <reified T : Any> NavGraphBuilder.composableScreen(
    useDefaultTransition: Boolean = true,
    customArgs: Map<KType, NavType<*>>? = null,
    deepLinks: List<NavDeepLink>? = null,
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) = composable<T>(
    typeMap = customArgs ?: emptyMap(),
    deepLinks = deepLinks ?: emptyList(),
    enterTransition = {
        if (useDefaultTransition) {
            slideIntoContainer(towards = Start, animationSpec = tween(350))
        } else fadeIn(tween(350))
    },
    exitTransition = {
        fadeOut(animationSpec = tween(350))
    },
    popEnterTransition = {
        fadeIn(animationSpec = tween(350))
    },
    popExitTransition = {
        if (useDefaultTransition) {
            slideOutOfContainer(towards = End, animationSpec = tween(350))
        } else fadeOut(tween(350))
    },
    content = content
)

/**
 * Custom NavType for serializing and deserializing objects using Kotlin Serialization.
 * This allows you to pass complex objects as arguments in the navigation graph.
 */
inline fun <reified T : Any> customNavType(
    isNullableAllowed: Boolean = false,
    json: Json = Json
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let<String, T>(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = Uri.encode(json.encodeToString(value))

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }
}

/**
 * Extension function to create a custom NavType for a specific type.
 */
inline fun <reified T : Any> generateCustomNavType() = typeOf<T>() to customNavType<T>()

/**
 * Extension function to set a value in the NavController's back stack entry's saved state handle.
 * @param key The key to set the value for.
 * @param value The value to set.
 */
inline fun <reified T> NavController.setBackPressedWithArgs(
    key: String,
    value: T
) {
    popBackStack()
    previousBackStackEntry?.savedStateHandle?.set(key, Json.encodeToString(value))
}

/**
 * Extension function to get a value from the NavController's back stack entry's saved state handle.
 * @param key The key to get the value for.
 * @return The value associated with the key, or null if not found.
 */
inline fun <reified T> NavController.getArgsWhenBackPressed(
    key: String
): T? = try {
    currentBackStackEntry?.savedStateHandle?.run {
        val result = get<String>(key).orEmpty()
        remove<String>(key)
        Json.decodeFromString(result)
    }
} catch (e: Exception) {
    e.printStackTrace()
    null
}

/**
 * Extension function to navigate to a specific route in the NavController.
 * @param route The route to navigate to.
 * @param popUpTo The route to pop up to.
 * @param inclusive Boolean flag to include the popped route.
 * @param saveState Boolean flag to save the state of the popped route.
 * @param launchSingleTop Boolean flag to launch the single top.
 * @param restoreState Boolean flag to restore the state.
 */
fun NavController.navigateTo(
    route: Any,
    popUpTo: Any? = null,
    inclusive: Boolean = false,
    saveState: Boolean = true,
    launchSingleTop: Boolean = false,
    restoreState: Boolean = true
) {
    navigate(route) {
        popUpTo?.let {
            popUpTo(it) {
                this.inclusive = inclusive
                this.saveState = saveState
            }
        }
        this.launchSingleTop = launchSingleTop
        this.restoreState = restoreState
    }
}

/**
 * Extension function to navigate to a specific route in the NavController and clear the back stack.
 */
fun NavController.navigateClearBackStack(route: Any) {
    navigate(route) {
        popUpTo(graph.id) {
            inclusive = false
            saveState = false
        }
        launchSingleTop = true
        restoreState = false
    }
}

/**
 * Extension function to create a deep link for a specific URI.
 * @param uri The URI pattern for the deep link.
 */
fun buildDeepLink(uri: String) = navDeepLink { uriPattern = uri }
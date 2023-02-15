package com.baec23.composetemplate.di

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.remote.api.baseUrl
import com.baec23.composetemplate.repository.PostRepository
import com.baec23.composetemplate.repository.SampleItemRepository
import com.baec23.composetemplate.service.SnackbarService
import com.here.oksse.OkSse
import com.here.oksse.ServerSentEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNavController(@ApplicationContext context: Context) =
        NavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }
    @Singleton
    @Provides
    fun provideNavigationService(navController: NavHostController) = NavService(navController = navController)
    @Singleton
    @Provides
    fun provideSnackbarService() = SnackbarService()

    @Singleton
    @Provides
    fun provideSampleItemRepository() = SampleItemRepository()

    @Singleton
    @Provides
    fun providePostRepository() = PostRepository()
}

package pl.programming.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import pl.programming.onboarding_domain.usecase.ValidateNutrients
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {

    @Provides
    @ViewModelScoped
    fun provideValidateNutriensUseCase(): ValidateNutrients {
        return ValidateNutrients()
    }
}
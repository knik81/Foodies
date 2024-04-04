package nti.team.foodies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import nti.team.domain.UseCaseApi
import nti.team.entity.RepositoryApiInterface
import nti.team.entity.UseCaseApiInterface

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideUseCaseApiInterface(repositoryApi: RepositoryApiInterface): UseCaseApiInterface {
        return UseCaseApi(repositoryApi)
    }
}
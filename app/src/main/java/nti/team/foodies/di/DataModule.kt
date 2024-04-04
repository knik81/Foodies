package nti.team.foodies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import nti.team.data.RepositoryApi
import nti.team.entity.RepositoryApiInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepositoryApi(repositoryApi: RepositoryApi): RepositoryApiInterface {
        return repositoryApi
    }



}


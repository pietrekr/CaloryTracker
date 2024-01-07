package pl.programming.trackerdata.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.programming.trackerdata.local.TrackerDatabase;
import pl.programming.trackerdata.remote.OpenFoodApi;
import pl.programming.trackerdomain.repository.TrackerRepository;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class TrackerDataModule_ProvideTrackerRepositoryFactory implements Factory<TrackerRepository> {
  private final Provider<OpenFoodApi> apiProvider;

  private final Provider<TrackerDatabase> dbProvider;

  public TrackerDataModule_ProvideTrackerRepositoryFactory(Provider<OpenFoodApi> apiProvider,
      Provider<TrackerDatabase> dbProvider) {
    this.apiProvider = apiProvider;
    this.dbProvider = dbProvider;
  }

  @Override
  public TrackerRepository get() {
    return provideTrackerRepository(apiProvider.get(), dbProvider.get());
  }

  public static TrackerDataModule_ProvideTrackerRepositoryFactory create(
      Provider<OpenFoodApi> apiProvider, Provider<TrackerDatabase> dbProvider) {
    return new TrackerDataModule_ProvideTrackerRepositoryFactory(apiProvider, dbProvider);
  }

  public static TrackerRepository provideTrackerRepository(OpenFoodApi api, TrackerDatabase db) {
    return Preconditions.checkNotNullFromProvides(TrackerDataModule.INSTANCE.provideTrackerRepository(api, db));
  }
}

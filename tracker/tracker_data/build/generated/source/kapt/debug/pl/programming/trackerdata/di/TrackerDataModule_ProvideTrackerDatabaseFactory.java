package pl.programming.trackerdata.di;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.programming.trackerdata.local.TrackerDatabase;

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
public final class TrackerDataModule_ProvideTrackerDatabaseFactory implements Factory<TrackerDatabase> {
  private final Provider<Application> appProvider;

  public TrackerDataModule_ProvideTrackerDatabaseFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public TrackerDatabase get() {
    return provideTrackerDatabase(appProvider.get());
  }

  public static TrackerDataModule_ProvideTrackerDatabaseFactory create(
      Provider<Application> appProvider) {
    return new TrackerDataModule_ProvideTrackerDatabaseFactory(appProvider);
  }

  public static TrackerDatabase provideTrackerDatabase(Application app) {
    return Preconditions.checkNotNullFromProvides(TrackerDataModule.INSTANCE.provideTrackerDatabase(app));
  }
}

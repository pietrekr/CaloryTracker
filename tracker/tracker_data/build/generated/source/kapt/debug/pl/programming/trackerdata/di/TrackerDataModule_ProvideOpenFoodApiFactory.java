package pl.programming.trackerdata.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import pl.programming.trackerdata.remote.OpenFoodApi;

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
public final class TrackerDataModule_ProvideOpenFoodApiFactory implements Factory<OpenFoodApi> {
  private final Provider<OkHttpClient> clientProvider;

  public TrackerDataModule_ProvideOpenFoodApiFactory(Provider<OkHttpClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public OpenFoodApi get() {
    return provideOpenFoodApi(clientProvider.get());
  }

  public static TrackerDataModule_ProvideOpenFoodApiFactory create(
      Provider<OkHttpClient> clientProvider) {
    return new TrackerDataModule_ProvideOpenFoodApiFactory(clientProvider);
  }

  public static OpenFoodApi provideOpenFoodApi(OkHttpClient client) {
    return Preconditions.checkNotNullFromProvides(TrackerDataModule.INSTANCE.provideOpenFoodApi(client));
  }
}

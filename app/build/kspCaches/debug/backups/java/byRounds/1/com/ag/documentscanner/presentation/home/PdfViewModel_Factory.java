package com.ag.documentscanner.presentation.home;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class PdfViewModel_Factory implements Factory<PdfViewModel> {
  private final Provider<Application> applicationProvider;

  public PdfViewModel_Factory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public PdfViewModel get() {
    return newInstance(applicationProvider.get());
  }

  public static PdfViewModel_Factory create(Provider<Application> applicationProvider) {
    return new PdfViewModel_Factory(applicationProvider);
  }

  public static PdfViewModel newInstance(Application application) {
    return new PdfViewModel(application);
  }
}

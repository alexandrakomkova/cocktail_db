package com.example.cocktail_db.presentation.onboarding_screen

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class OnboardingStorage: KoinComponent {
		// in production app data storage or shared preference should be used

		private val _onboardingState = MutableStateFlow<OnboardingState>(OnboardingState.NotOnboarded)
		val onboardingState = _onboardingState.asStateFlow()

		fun onOnboarded() {
				_onboardingState.value = OnboardingState.Onboarded
		}
}
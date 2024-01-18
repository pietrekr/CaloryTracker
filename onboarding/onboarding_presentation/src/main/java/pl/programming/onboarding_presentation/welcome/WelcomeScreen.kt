package pl.programming.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import pl.programming.core.R
import pl.programming.coreui.LocalSpacing
import pl.programming.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        ActionButton(
            text = stringResource(R.string.next),
            onClick = { onNextClick() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

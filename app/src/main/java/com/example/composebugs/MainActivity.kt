package com.example.composebugs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.composebugs.ui.theme.ComposeBugsTheme


class MainActivity : ComponentActivity() {

    private val WELL_TEXT_FIELD_TEST_TAG: String = "foo"

    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = SnackbarHostState()
            val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)

            ProfileScreen(
                scaffoldState = scaffoldState
            )


        }
    }



    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun greenfield(softwareKeyboardController: SoftwareKeyboardController?) {
        ComposeBugsTheme {
            Scaffold(
                topBar = {
                    TopAppBar() {

                    }
                },
                bottomBar = {
                    Button(onClick = { /*TODO*/ }) {
                        Text("Button")
                    }
                }) {

                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .testTag("bar")
                ) {
                    var uno = remember { mutableStateOf(TextFieldValue("")) }
                    var dos = remember { mutableStateOf(TextFieldValue("")) }
                    var tres = remember { mutableStateOf(TextFieldValue("")) }
                    var cuatro = remember { mutableStateOf(TextFieldValue("")) }


                    Column(
                        modifier = Modifier.testTag("foobar")
                    ) {
                        ListItemView(title = "foo", subtitle = "bar", onClick = {})
                        ListItemView(title = "foo", subtitle = "bar", onClick = {})
                        ListItemView(title = "foo", subtitle = "bar", onClick = {})
                        ListItemView(title = "foo", subtitle = "bar", onClick = {})
                        ListItemView(title = "foo", subtitle = "bar", onClick = {})
                        ListItemView(title = "foo", subtitle = "bar", onClick = {})
                        ListItemView(title = "foo", subtitle = "bar", onClick = {})

                        TextField(
                            uno.value,
                            onValueChange = {
                                uno.value = it
                            },
                            modifier = Modifier
                                .testTag(WELL_TEXT_FIELD_TEST_TAG)
                                .fillMaxWidth(),
                            placeholder = {
                                Text("uno")
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    softwareKeyboardController?.hide()
                                },
                            ),
                            singleLine = true
                        )
                    }

                    TextField(
                        dos.value, onValueChange = {
                            dos.value = it
                        },
                        modifier = Modifier
                            .testTag(WELL_TEXT_FIELD_TEST_TAG)
                            .fillMaxWidth(),
                        placeholder = {
                            Text("dos")
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                softwareKeyboardController?.hide()
                            },
                        ),
                        singleLine = true
                    )
                    TextField(
                        tres.value, onValueChange = {
                            tres.value = it
                        },
                        modifier = Modifier
                            .testTag(WELL_TEXT_FIELD_TEST_TAG)
                            .fillMaxWidth(),
                        placeholder = {
                            Text("tres")
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                softwareKeyboardController?.hide()
                            },
                        ),
                        singleLine = true
                    )


                    bogusField()
                    bogusField()

                    TextField(
                        cuatro.value,
                        onValueChange = {
                            cuatro.value = it
                        },
                        modifier = Modifier
                            .testTag(WELL_TEXT_FIELD_TEST_TAG)
                            .fillMaxWidth(),
                        placeholder = {
                            Text("cuatro")
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                softwareKeyboardController?.hide()
                            },
                        ),
                        singleLine = true
                    )

                    bogusField()
                    bogusField()
                    bogusField()
                    bogusField()
                    bogusField()
                }
            }
        }
    }
}


@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    emailClicked: () -> Unit = {},
    phoneClicked: () -> Unit = {},
    passwordClicked: () -> Unit = {},
    updateFirstName: (String) -> Unit = {},
    updateLastName: (String) -> Unit = {},
    updatePreferredName: (String) -> Unit = {},
    updateDateOfBirth: (String) -> Unit = {},
    updateAddressOne: (String) -> Unit = {},
    updateAddressTwo: (String) -> Unit = {},
    updateCity: (String) -> Unit = {},
    updateZipCode: (String) -> Unit = {},

) {
    MaterialTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("foo") },
                    navController = navController
                )
            },
            bottomBar = {
                BottomAppBar() {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { saveProfileChanges.invoke() }
                    ) {
                        Text("bar")
                    }
                }
            }
        ) { paddingValues ->


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ListItemView(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    title = "stringResource(R.string.email)",
                    subtitle = "content.consumer.primaryEmail",
                    bottomView = {
                        UnverifiedBubble()
                    },
                    onClick = {
                        emailClicked.invoke()
                    }
                )
                ListItemView(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    title = "phone",
                    subtitle = "please",
                    bottomView = {
                        UnverifiedBubble()
                    },
                    onClick = {
                        phoneClicked.invoke()
                    }
                )
                ListItemView(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    title = "password",
                    subtitle = "\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF",
                    onClick = {
                        passwordClicked.invoke()
                    }
                )

                var firstName by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.firstName")
                    )
                }
                var lastName by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.lastName")
                    )
                }
                var preferredName by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.preferredName.orEmpty()")
                    )
                }
                var dateOfBirth by remember {
                    mutableStateOf(
                        TextFieldValue("content.dateOfBirthText")
                    )
                }
                var gender by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.gender.stringRes")
                    )
                }
                var sex by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.sexAtBirth.stringRes")
                    )
                }
                var addressOne by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.getPreferredAddress()?.addressOne.orEmpty()")
                    )
                }
                var addressTwo by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.getPreferredAddress()?.addressTwo.orEmpty()")
                    )
                }
                var city by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.getPreferredAddress()?.city.orEmpty()")
                    )
                }
                var state by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.getPreferredAddress()?.state.orEmpty()")
                    )
                }
                var zipCode by remember {
                    mutableStateOf(
                        TextFieldValue("content.consumer.getPreferredAddress()?.zipCode.orEmpty()")
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    WellTextField(
                        modifier = Modifier.weight(1f),
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            updateFirstName.invoke(it.text)
                        },
                        label = "stringResource(R.string.first_name)",
                        errorMessage = "content.firstNameError"
                    )
                    WellTextField(
                        modifier = Modifier.weight(1f),
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            updateLastName.invoke(it.text)
                        },
                        label = "stringResource(R.string.last_name)",
                        errorMessage = "content.lastNameError"
                    )
                }
                WellTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            start = spacingHorizontal,
                            end = spacingHorizontal
                        ),
                    value = preferredName,
                    onValueChange = {
                        preferredName = it
                        updatePreferredName.invoke(it.text)
                    },
                    label = stringResource(R.string.preferred_name),
                    errorMessage = content.preferredNameError
                )
                WellTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            start = spacingHorizontal,
                            end = spacingHorizontal
                        ),
                    value = dateOfBirth,
                    onValueChange = {
                        dateOfBirth = it
                        updateDateOfBirth.invoke(it.text)
                    },
                    inputType = InputType.DATE,
                    label = stringResource(R.string.date_of_birth),
                    errorMessage = content.dobError
                )
                WellTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                        ),
                    value = addressOne,
                    onValueChange = {
                        addressOne = it
                        updateAddressOne.invoke(it.text)
                    },
                    label = "stringResource(R.string.address_line_1)"
                )
                WellTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,

                        ),
                    value = addressTwo,
                    onValueChange = {
                        addressTwo = it
                        updateAddressTwo.invoke(it.text)
                    },
                    label = "stringResource(R.string.address_line_2)"
                )
                WellTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,

                        ),
                    value = city,
                    onValueChange = {
                        city = it
                        updateCity.invoke(it.text)
                    },
                    label = stringResource(R.string.city)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .weight(1f)
                    ) {
                    }
                    WellTextField(
                        modifier = Modifier
                            .weight(1f),
                        value = zipCode,
                        onValueChange = {
                            zipCode = it
                            updateZipCode.invoke(it.text)
                        },
                        label = "stringResource(R.string.zip_code)",
                        errorMessage = "content.zipCodeError"
                    )
                }
            }


        }
    }
}


@Composable
private fun UnverifiedBubble() {
    Text("foobar")
}


val bogusTag = "bogusTag"
val bogusField = "bogusField"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun bogusField() {
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    var bogus = remember { mutableStateOf(TextFieldValue("")) }



    TextField(
        bogus.value, onValueChange = {
            bogus.value = it
        },
        modifier = Modifier
            .testTag(bogusTag)
            .fillMaxWidth(),
        placeholder = {
            Text(bogusField)
        },
        keyboardActions = KeyboardActions(
            onDone = {
                softwareKeyboardController?.hide()
            },
        ),
        singleLine = true
    )
}



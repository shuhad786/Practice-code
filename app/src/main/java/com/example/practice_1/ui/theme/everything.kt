package com.example.practice_1.ui.theme

1. Text
In Jetpack Compose, you can easily style text using the Text composable. Here’s how to apply different styles:
kotlin


@Composable
fun StyledText() {
  Text(
    text = "Hello, Jetpack Compose!",
    style = TextStyle(
      color = Color.Red,
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      textDecoration = TextDecoration.LineThrough,
      textAlign = TextAlign.Center
    ),
    modifier = Modifier.padding(16.dp)
  )
}

2. Button
Creating a button with custom styles is straightforward. You can set colors, padding, and click actions:
kotlin


@Composable
fun StyledButton(onClick: () -> Unit) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
    modifier = Modifier.padding(16.dp)
  ) {
    Text("Click Me", color = Color.White)
  }
}

3. TextField
For user input, you can use TextField with styling options:
kotlin


@Composable
fun StyledTextField(value: String, onValueChange: (String) -> Unit) {
  TextField(
    value = value,
    onValueChange = onValueChange,
    label = { Text("Enter text") },
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.LightGray,
      focusedIndicatorColor = Color.Blue,
      unfocusedIndicatorColor = Color.Gray
    ),
    modifier = Modifier.padding(16.dp)
  )
}

4. Image
Displaying images can also be styled. Here’s how to set an image with a specific content description:
kotlin


@Composable
fun StyledImage() {
  Image(
    painter = painterResource(id = R.drawable.sample_image),
    contentDescription = "Sample Image",
    modifier = Modifier
      .size(128.dp)
      .padding(16.dp)
      .clip(RoundedCornerShape(8.dp))
  )
}

5. Using MaterialTheme
To maintain consistency across your app, you can define a theme using MaterialTheme. Here’s how to set it up:
kotlin


@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colors = lightColors(
      primary = Color(0xFF6200EE),
      secondary = Color(0xFF03DAC5)
    ),
    typography = Typography(
      body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
      )
    ),
    shapes = Shapes(
      small = RoundedCornerShape(4.dp),
      medium = RoundedCornerShape(4.dp),
      large = RoundedCornerShape(0.dp)
    )
  ) {
    content()
  }
}

6. Putting It All Together
You can use these components in your main activity like this:
kotlin


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyAppTheme {
        Column {
          StyledText()
          StyledButton { /* Handle click */ }
          StyledTextField(value = "", onValueChange = { /* Handle text change */ })
          StyledImage()
        }
      }
    }
  }
}

1. Using Row for Horizontal Arrangement
The Row composable arranges its children horizontally. You can control the arrangement and alignment of its children using the horizontalArrangement and verticalAlignment parameters.
kotlin


@Composable
fun HorizontalArrangementExample() {
  Row(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween, // Distributes space between children
    verticalAlignment = Alignment.CenterVertically // Aligns children vertically in the center
  ) {
    Text("Item 1")
    Text("Item 2")
    Text("Item 3")
  }
}

2. Using Column for Vertical Arrangement
The Column composable arranges its children vertically. Similar to Row, you can use verticalArrangement and horizontalAlignment to control the layout.
kotlin


@Composable
fun VerticalArrangementExample() {
  Column(
    modifier = Modifier.fillMaxHeight().padding(16.dp),
    verticalArrangement = Arrangement.SpaceAround, // Distributes space around children
    horizontalAlignment = Alignment.Start // Aligns children to the start of the column
  ) {
    Text("Item A")
    Text("Item B")
    Text("Item C")
  }
}

3. Using Box for Overlapping Elements
The Box composable allows you to stack elements on top of each other. You can use the align modifier to position children within the box.
kotlin


@Composable
fun BoxAlignmentExample() {
  Box(
    modifier = Modifier.size(200.dp).background(Color.LightGray)
  ) {
    Text(
      text = "Top Left",
      modifier = Modifier.align(Alignment.TopStart) // Aligns to the top left
    )
    Text(
      text = "Bottom Right",
      modifier = Modifier.align(Alignment.BottomEnd) // Aligns to the bottom right
    )
  }
}

4. Combining Layouts
You can also combine Row and Column to create more complex layouts. Here’s an example that uses both:
kotlin


@Composable
fun CombinedLayoutExample() {
  Column(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("Row Item 1")
      Text("Row Item 2")
    }
    Text("Column Item 1")
    Text("Column Item 2")
  }
}

Step 1: Set Up Navigation
First, ensure you have the necessary dependencies for Jetpack Compose Navigation in your build.gradle file:
groovy


dependencies {
  implementation "androidx.navigation:navigation-compose:2.4.0"
}
Step 2: Create the Main Screen
In the main screen, you can have a TextField for user input and a button to navigate to the new screen, passing the input data.
kotlin


@Composable
fun MainScreen(navController: NavController) {
  var text by remember { mutableStateOf("") }
  var number by remember { mutableStateOf(0) }

  Column(modifier = Modifier.padding(16.dp)) {
    TextField(
      value = text,
      onValueChange = { text = it },
      label = { Text("Enter some text") }
    )
    TextField(
      value = number.toString(),
      onValueChange = { number = it.toIntOrNull() ?: 0 },
      label = { Text("Enter a number") },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    Button(onClick = {
      navController.navigate("second_screen/$text/$number")
    }) {
      Text("Go to Second Screen")
    }
  }
}
Step 3: Create the Second Screen
The second screen will receive the data passed from the main screen and display it.
kotlin


@Composable
fun SecondScreen(text: String, number: Int) {
  Column(modifier = Modifier.padding(16.dp)) {
    Text(text = "Received Text: $text", style = MaterialTheme.typography.h6)
    Text(text = "Received Number: $number", style = MaterialTheme.typography.h6)
  }
}
Step 4: Set Up Navigation Graph
Now, set up the navigation graph to handle the navigation between the two screens.
kotlin


@Composable
fun NavigationGraph(navController: NavController) {
  NavHost(navController, startDestination = "main_screen") {
    composable("main_screen") { MainScreen(navController) }
    composable("second_screen/{text}/{number}") { backStackEntry ->
      val text = backStackEntry.arguments?.getString("text") ?: ""
      val number = backStackEntry.arguments?.getString("number")?.toInt() ?: 0
      SecondScreen(text, number)
    }
  }
}
Step 5: Set Up the Main Activity
Finally, set up your MainActivity to host the navigation graph.
kotlin


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      NavigationGraph(navController)
    }
  }
}

Here are the necessary imports you would need for the Jetpack Compose examples provided earlier:
kotlin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
Explanation of Imports:
Activity and Compose Setup:
android.os.Bundle: For the activity lifecycle.
androidx.activity.ComponentActivity: Base class for activities that use Jetpack Compose.
androidx.activity.compose.setContent: To set the content view using Compose.
Compose UI Components:
androidx.compose.foundation.*: For foundational UI components like Column, Row, Box, and layout modifiers.
androidx.compose.material.*: For Material Design components like Button, Text, and TextField.
androidx.compose.runtime.*: For state management and composable functions.
androidx.compose.ui.*: For UI-related classes, including Modifier, Alignment, and Color.
Navigation:
androidx.navigation.NavController: For managing navigation between screens.
androidx.navigation.compose.*: For navigation-related functions and composables.

1. Inline onClick Handler
kotlin


@Composable
fun SimpleInlineButton() {
  Button(
    onClick = {
      // Direct inline action
      println("Button clicked directly!")
      // You can add multiple actions here
      Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
    }
  ) {
    Text("Inline Click Handler")
  }
}
2. Separate Function onClick Handler
kotlin


@Composable
fun ButtonWithSeparateFunction() {
  // Define a separate function for the click handler
  fun handleButtonClick() {
    println("Button clicked via separate function!")
    // Perform specific actions
  }

  Button(onClick = { handleButtonClick() }) {
    Text("Separate Function Click")
  }
}
3. State-Based Button with Counter
kotlin


@Composable
fun CounterButton() {
  var count by remember { mutableStateOf(0) }

  Column {
    Button(onClick = { count++ }) {
      Text("Clicked $count times")
    }
  }
}
4. Multiple Action Button
kotlin


@Composable
fun MultiActionButton() {
  Button(
    onClick = {
      // Multiple actions in a single click handler
      performFirstAction()
      updateUIState()
      showNotification()
    }
  ) {
    Text("Multi-Action Button")
  }
}

// Supporting functions
fun performFirstAction() {
  // Some specific action
  println("First action performed")
}

fun updateUIState() {
  // Update some UI state
  println("UI state updated")
}

fun showNotification() {
  // Show a notification
  println("Notification shown")
}
5. Conditional Button Interaction
kotlin


@Composable
fun ConditionalButton() {
  var isEnabled by remember { mutableStateOf(true) }

  Button(
    onClick = {
      // Toggle button state or perform action
      isEnabled = !isEnabled
    },
    enabled = isEnabled // Dynamically enable/disable button
  ) {
    Text(if (isEnabled) "Click Me" else "Disabled")
  }
}
6. Navigation Button
kotlin


@Composable
fun NavigationButton(navController: NavController) {
  Button(
    onClick = {
      // Navigate to another screen
      navController.navigate("destination_screen")
    }
  ) {
    Text("Go to Next Screen")
  }
}
Key Principles
Inline Handlers: Quick, simple actions directly in the onClick
Separate Functions: Better for complex logic or reusable actions
State Management: Use remember and mutableStateOf to track and update button-related states
Flexibility: Can include multiple actions, conditionals, and navigation
Best Practices
Keep click handlers concise
Use separate functions for complex logic
Manage state effectively
Consider performance when adding multiple actions
Remember to import necessary components:
kotlin


import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

Here's a simple example of how to create increment and decrement buttons using Jetpack Compose. This example will demonstrate how to manage a counter value that can be increased or decreased by clicking the respective buttons.
Increment and Decrement Example
kotlin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CounterScreen()
    }
  }
}

@Composable
fun CounterScreen() {
  var count by remember { mutableStateOf(0) } // State to hold the counter value

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = "Counter: $count", style = MaterialTheme.typography.h4)

    Spacer(modifier = Modifier.height(16.dp))

    Row {
      Button(onClick = { count-- }) {
        Text("Decrement")
      }

      Spacer(modifier = Modifier.width(16.dp))

      Button(onClick = { count++ }) {
        Text("Increment")
      }
    }
  }
}
Explanation of the Code:
State Management:
We use var count by remember { mutableStateOf(0) } to create a mutable state variable that holds the current count. The remember function ensures that the state is preserved across recompositions.
UI Layout:
A Column is used to arrange the text and buttons vertically. The horizontalAlignment and verticalArrangement parameters help center the content.
Text Display:
The current count is displayed using a Text composable.
Buttons:
Two buttons are created: one for decrementing the count and another for incrementing it. The onClick lambda expressions update the count variable accordingly.
Spacing:
Spacer is used to add space between the text and buttons, as well as between the buttons themselves.

Here's a complete example of how to use Jetpack Compose with a NavHost to pass values from TextField inputs and integers to a new screen. This example will demonstrate how to set up navigation and pass data effectively.
Step 1: Set Up Dependencies
Make sure you have the necessary dependencies for Jetpack Compose and Navigation in your build.gradle file:
groovy


dependencies {
  implementation "androidx.navigation:navigation-compose:2.4.0"
  // Other dependencies...
}
Step 2: Create the Main Activity
In your MainActivity, set up the navigation graph and the initial screen.
kotlin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        Surface {
          NavigationGraph()
        }
      }
    }
  }
}
Step 3: Create the Navigation Graph
Define the navigation graph that includes the main screen and the second screen.
kotlin


@Composable
fun NavigationGraph() {
  val navController = rememberNavController()
  NavHost(navController, startDestination = "main_screen") {
    composable("main_screen") { MainScreen(navController) }
    composable("second_screen/{text}/{number}") { backStackEntry ->
      val text = backStackEntry.arguments?.getString("text") ?: ""
      val number = backStackEntry.arguments?.getString("number")?.toInt() ?: 0
      SecondScreen(text, number)
    }
  }
}
Step 4: Create the Main Screen
In the main screen, create TextField inputs for text and an integer, and a button to navigate to the second screen.
kotlin


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(navController: NavController) {
  var text by remember { mutableStateOf("") }
  var number by remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    TextField(
      value = text,
      onValueChange = { text = it },
      label = { Text("Enter some text") }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
      value = number,
      onValueChange = { number = it },
      label = { Text("Enter a number") }
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
      navController.navigate("second_screen/$text/${number.toIntOrNull() ?: 0}")
    }) {
      Text("Go to Second Screen")
    }
  }
}
Step 5: Create the Second Screen
In the second screen, display the received text and number.
kotlin


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(text: String, number: Int) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = "Received Text: $text")
    Text(text = "Received Number: $number")
  }
}

Certainly! Below is an example of how to implement multiple screens within a single MainActivity using Jetpack Compose. This example will demonstrate how to navigate between two screens while passing data from TextField inputs and integers.
Complete Code Example in MainActivity
kotlin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        Surface {
          NavigationGraph()
        }
      }
    }
  }
}

@Composable
fun NavigationGraph() {
  val navController = rememberNavController()
  NavHost(navController, startDestination = "main_screen") {
    composable("main_screen") { MainScreen(navController) }
    composable("second_screen/{text}/{number}") { backStackEntry ->
      val text = backStackEntry.arguments?.getString("text") ?: ""
      val number = backStackEntry.arguments?.getString("number")?.toInt() ?: 0
      SecondScreen(text, number)
    }
  }
}

@Composable
fun MainScreen(navController: NavController) {
  var text by remember { mutableStateOf("") }
  var number by remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    TextField(
      value = text,
      onValueChange = { text = it },
      label = { Text("Enter some text") }
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
      value = number,
      onValueChange = { number = it },
      label = { Text("Enter a number") }
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
      navController.navigate("second_screen/$text/${number.toIntOrNull() ?: 0}")
    }) {
      Text("Go to Second Screen")
    }
  }
}

@Composable
fun SecondScreen(text: String, number: Int) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = "Received Text: $text")
    Text(text = "Received Number: $number")
  }
}
Explanation of the Code:
MainActivity: This is the entry point of the application. It sets up the content using Jetpack Compose and initializes the navigation graph.
NavigationGraph: This composable defines the navigation structure. It uses NavHost to manage the navigation between the main screen and the second screen.
MainScreen: This screen contains two TextField inputs for the user to enter text and a number. When the button is clicked, it navigates to the second screen, passing the entered values as arguments.
SecondScreen: This screen receives the text and number as parameters and displays them.
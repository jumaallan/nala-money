## \[ 🚧 Work in progress 👷‍♀️⛏👷🔧️🚧 \] NALA Money

A simple android app that shows your transactions. In this solution, the following needs to be demonstrated 

- [x] Fix the TransactionViewModel.getAllTransactions(), you can use the AppExecutors in money.nala.pay.interview.executers
- [x] Show list header view (li_transaction_header.xml) to the top of Transactions List
- [x] Add action to open a new screen on transaction click and the show/hide transaction balance on header show/hide click
- [x] How would you localize the app
- [x] How to support RTL support for Arabic
- [X] Enrich unit tests for toSafeDouble() in StringUtilsTests and add a couple of test cases for formatAmountCommas()

 ## Project characteristics

This project brings to table set of best practices, tools, and solutions:

* 100% Kotlin
* Model-View-ViewModel Architecture
* Android Jetpack
* Testing
* Dependency Injection
* Material design
 

 ## Tech-stack
 
This project takes advantage of many popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version.
 
 * Tech-stack
     * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
     * [KOIN](https://insert-koin.io/) - dependency injection
     * [Jetpack](https://developer.android.com/jetpack)
         * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
         * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes
         * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
     * [Stetho](http://facebook.github.io/stetho/) - application debugging tool
 
 * Architecture
     * MVVM - application level
     * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)
 * Tests
     * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
 
 ## Getting started
 
 There are a few ways to open this project.
 
 ### Android Studio
 
 1. Android Studio -> File -> New -> From Version control -> Git
 2. Enter `https://github.com/jumaallan/nala-money.git` into URL field
 
 ### Command line + Android Studio
 
 1. Run `git clone https://github.com/jumaallan/nala-money.git`
 2. Android Studio -> File -> Open
 
 ## License
 ```
 MIT License
 
 Copyright (c) 2020 Juma Allan
 
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
 associated documentation files (the "Software"), to deal in the Software without restriction, including 
 without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to 
 the following conditions:
 
 The above copyright notice and this permission notice shall be included in all copies or substantial 
 portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN 
 NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 WHETHER IN AN ACTION OF  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ```
TakeawayChallenge is a sample Android application written in Kotlin. 

I put the given json file into an endpoint to make the data retrieval more realistic 



# Technologies used

This project is developped in Kotlin, and uses [the CLEAN architecture.](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

![cleanarchitecture](https://user-images.githubusercontent.com/4358453/50484958-7966e480-09f3-11e9-8ac6-bb138e2595e9.jpg)

# Main libraries used

* DaggerAndroid
* RxJava2
* Retrofit2
* Room

# Layers


* `data/` : contains the code to access to the data: Data classes and Mappers (repository pattern)
* `domain/` : contains the business logic and the Usecases
* `presentation/` : presentation layer, contains the UI: Activities, Fragmetns and ViewModels (MVVM pattern)
& `framework/` - contains framework classes like Retrofit and Room classes

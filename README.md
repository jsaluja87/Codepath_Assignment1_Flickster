# Project 1 - *Flickster*

**Flickster** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user

Time spent: **20** hours spent in total

## User Stories

The following **required** functionality is completed:

* [Y] User can **scroll through current movies** from the Movie Database API
* [Y] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [Y] For each movie displayed, user can see the following details:
  * [Y] Title, Poster Image, Overview (Portrait mode)
  * [Y] Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* [Y] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.
* [Y] Improved the user interface through styling and coloring.

The following **bonus** features are implemented:

* [Y] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [Y] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [Y] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [Y] Overlay a play icon for videos that can be played.
    * [Y] More popular movies should start a separate activity that plays the video immediately.
    * [Y] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [Y] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [Y] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [Y] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) networking libraries.

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/jsaluja87/Codepath_Assignment1_Flickster/blob/master/Codepath_Assignment1_Flickster_portrait.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='https://github.com/jsaluja87/Codepath_Assignment1_Flickster/blob/master/Codepath_Assignment1_Flickster_landscape.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

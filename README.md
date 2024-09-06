# Effortless Concurrency with Arrow-FX on Android

Slides and sample code for the Effortless Concurrency with Arrow-FX on Android talk.

To run it in a local environment using reveal.js execute:

```
cd deck
npm install
npm start
```

## Presented at

- [Droidcon Lisbon 2024][dclx24]

## Abstract

The Kotlinx Coroutines library enables developers to write responsive, scalable, safe, and efficient code for asynchronous APIs. However, it can sometimes be limited when dealing with numerous suspended computations.

Arrow-Fx expands on the capabilities of the Kotlinx coroutines library with additional functions. In this presentation, we'll explore a practical scenario involving an Android application communicating with a backend via a REST API. Utilizing Arrow-Fx operators, our goal is to simplify our code and enhance its efficiency. Additionally, we'll delve into the Schedule data type, enabling us to build resilient apps that gracefully handle failures through compositional retry mechanisms.

By the end of this talk, attendees will discover how the Arrow-Fx library can drastically simplify the development process, allowing them to create powerful and efficient Android applications with minimal effort.

[dclx24]: https://www.lisbon.droidcon.com/
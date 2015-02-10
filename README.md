[![Build
Status](https://snap-ci.com/clojure-glasgow/loneworkercheckin/branch/master/build_image)](https://snap-ci.com/clojure-glasgow/loneworkercheckin/branch/master)

# checkin

A Clojure library designed to ... well, that part is up to you.

## Usage

Start the app with `lein ring server`

To proxy all your server-side HTTP and HTTPS calls through a proxy start the app with `lein run-proxied`
This will proxy all calls through 127.0.0.1:8888, which allows you to use a proxy such as [Charles](http://www.charlesproxy.com/) to intercept traffic. This is useful when debugging 3rd part authentication requests e.g. Facebook. If you're running with the proxy you'll need to tell Java to trust your proxy's SSL cert. Instructions for doing this with [Charles](http://www.charlesproxy.com/) are at [http://www.charlesproxy.com/documentation/using-charles/ssl-certificates/](http://www.charlesproxy.com/documentation/using-charles/ssl-certificates/)

To proxy your server HTTP and HTTPS calls

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

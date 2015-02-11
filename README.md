[![Build
Status](https://snap-ci.com/clojure-glasgow/loneworkercheckin/branch/master/build_image)](https://snap-ci.com/clojure-glasgow/loneworkercheckin/branch/master)

# checkin

“As a lone worker

I want to be able to add diary appointments in advance and share them privately with one or more colleagues (friends or family) and then, at the appropriate time, check-in to confirm that I am where I planned to be

So that my colleagues are informed automatically if I do not check-in and can raise the alarm.”

The Victims Support charity has been focusing on individuals that have been subject to attacks whilst alone in working situations. There has been a significant increase in assaults on lone workers; such as health professionals, sales people, maintenance professionals, estate agents and anyone else who’s workplace isn’t limited to specific locations or where the location is remote and there aren’t any colleagues.

The use of location aware apps has grown significantly recently but they all seem to rely on the publication of location data which may be made available to the general public, using such services has well known privacy implications and security problems including enabling criminals to know when someone is not at home and / or where someone is next due.

A major goal of the project is to protect privacy and be as secure as possible.

## Usage

Start the app with `lein ring server`

To proxy all your server-side HTTP and HTTPS calls through a proxy start the app with `lein run-proxied`
This will proxy all calls through 127.0.0.1:8888, which allows you to use a proxy such as [Charles](http://www.charlesproxy.com/) to intercept traffic. This is useful when debugging 3rd part authentication requests e.g. Facebook. If you're running with the proxy you'll need to tell Java to trust your proxy's SSL cert. Instructions for doing this with [Charles](http://www.charlesproxy.com/) are at [http://www.charlesproxy.com/documentation/using-charles/ssl-certificates/](http://www.charlesproxy.com/documentation/using-charles/ssl-certificates/)

To proxy your server HTTP and HTTPS calls

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[![Build
Status](https://snap-ci.com/clojure-glasgow/loneworkercheckin/branch/master/build_image)](https://snap-ci.com/clojure-glasgow/loneworkercheckin/branch/master)

# checkin

“As a lone worker

I want to be able to add diary appointments in advance and share them privately with one or more colleagues (friends or family) and then, at the appropriate time, check-in to confirm that I am where I planned to be

So that my colleagues are informed automatically if I do not check-in and can raise the alarm.”

The Victims Support charity has been focusing on individuals that have been subject to attacks whilst alone in working situations. There has been a significant increase in assaults on lone workers; such as health professionals, sales people, maintenance professionals, estate agents and anyone else who’s workplace isn’t limited to specific locations or where the location is remote and there aren’t any colleagues.

This project has been inspired by the charity: http://www.suzylamplugh.org/about-us/ - they already list lots of applications that help with personal safety (http://www.suzylamplugh.org/personal-safety-tips/app-directory/) but they all require paying for and they all seem a little Big Brother, in that they are top down organisation imposed implementations.

The goal of this project is to offer an alternative that an individual can opt into, being sure that their data is private, that it is only retained for as long as it is useful and that it's not a tool that can be used for snooping.

Privacy and security of the user's data is a major goal of this project.

## Usage

Start the app with `lein ring server`

To proxy all your server-side HTTP and HTTPS calls through a proxy start the app with `lein run-proxied`
This will proxy all calls through 127.0.0.1:8888, which allows you to use a proxy such as [Charles](http://www.charlesproxy.com/) to intercept traffic. This is useful when debugging 3rd part authentication requests e.g. Facebook. If you're running with the proxy you'll need to tell Java to trust your proxy's SSL cert. Instructions for doing this with [Charles](http://www.charlesproxy.com/) are at [http://www.charlesproxy.com/documentation/using-charles/ssl-certificates/](http://www.charlesproxy.com/documentation/using-charles/ssl-certificates/)

To proxy your server HTTP and HTTPS calls

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

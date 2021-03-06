(defproject fred-admin "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj"]
  :repl-options {:timeout 200000} ;; Defaults to 30000 (30 seconds)

  :test-paths ["spec/clj"]

  :dependencies [[leiningen "2.5.1"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3123" :scope "provided"]
                 [ring "1.3.2"]
                 [ring/ring-defaults "0.1.4"]
                 [compojure "1.3.2"]
                 [enlive "1.1.5"]
                 [om "0.8.0-rc1"]
                 [racehub/om-bootstrap "0.4.2"]
                 [environ "1.0.0"]
                 [secretary "1.2.2"]
                 [http-kit "2.1.19"]
                 [prismatic/om-tools "0.3.11"]]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-environ "1.0.0"]]

  :min-lein-version "2.5.1"

  :uberjar-name "fred-admin.jar"

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to
                                        "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :source-map
                                        "resources/public/js/out.js.map"
                                        :preamble      ["react/react.min.js"]
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :profiles {:dev {:source-paths ["env/dev/clj"]
                   :test-paths ["test/clj"]

                   :dependencies [[figwheel "0.2.5"]
                                  [figwheel-sidecar "0.2.5"]
                                  [com.cemerick/piggieback "0.1.5"]
                                  [weasel "0.6.0"]
                                  [speclj "3.1.0"]]

                   :repl-options {:init-ns fred-admin.server
                                  :nrepl-middleware
                                  [cemerick.piggieback/wrap-cljs-repl]}

                   :plugins [[lein-figwheel "0.2.1-SNAPSHOT"]
                             [speclj "3.1.0"]]

                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :css-dirs ["resources/public/css"]}

                   :env {:is-dev true}

                   :cljsbuild {:test-commands
                               { "spec" ["phantomjs" "bin/speclj"
                                         "resources/public/js/app_test.js"] }
                               :builds {:app {:source-paths ["env/dev/cljs"]}
                                        :test
                                        {:source-paths ["src/cljs" "spec/cljs"]
                                         :compiler {:output-to     "resources/public/js/app_test.js"
                                                    :output-dir    "resources/public/js/test"
                                                    :source-map    "resources/public/js/test.js.map"
                                                    :preamble      ["react/react.min.js"]
                                                    :optimizations :whitespace
                                                    :pretty-print  false}}}}}

             :uberjar {:source-paths ["env/prod/clj"]
                       :hooks [leiningen.cljsbuild]
                       :env {:production true}
                       :omit-source true
                       :aot :all
                       :cljsbuild {:builds {:app
                                            {:source-paths ["env/prod/cljs"]
                                             :compiler
                                             {:optimizations :advanced
                                              :pretty-print false}}}}}})

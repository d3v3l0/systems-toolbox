(defproject matthiasn/systems-toolbox "0.6.41"
  :description "Toolbox for building Systems in Clojure"
  :url "https://github.com/matthiasn/systems-toolbox"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/cljc"]

  :dependencies [[org.clojure/core.match "0.3.0"]
                 [org.clojure/tools.logging "0.5.0"]
                 [io.aviso/pretty "0.1.37"]
                 [expound "0.8.2"]
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"]]

  :plugins [[lein-codox "0.10.7"]
            [test2junit "1.4.2"]
            [lein-doo "0.1.11"]
            [lein-cloverage "1.1.2"]
            [lein-ancient "0.6.15"]
            [com.jakemccrary/lein-test-refresh "0.24.1"]
            [lein-cljsbuild "1.1.7"]]

  :test2junit-output-dir
  ~(or (System/getenv "CIRCLE_TEST_REPORTS") "target/test2junit")

  :test-refresh {:notify-on-success false
                 :changes-only      false}

  :clean-targets ^{:protect false} ["target/" "out/"]

  :test-paths ["test"]
  ;:test-paths ["dev-resources" "test" "perf"]

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.1"]
                                  [org.clojure/clojurescript "1.10.597"]
                                  [org.clojure/core.async "0.6.532"]
                                  [ch.qos.logback/logback-classic "1.2.3"]]
                   :jvm-opts     ["-Dclojure.compiler.direct-linking=true"]}}

  :cljsbuild
  {:builds [{:id           "cljs-test"
             :source-paths ["src" "test"]
             :compiler     {:output-to     "out/testable.js"
                            :main          matthiasn.systems-toolbox.runner
                            :target        :nodejs
                            :optimizations :advanced}}
            {:id           "cljs-perf-test"
             :source-paths ["perf" "src" "test"]
             :compiler     {:output-to     "out/perf.js"
                            :target        :nodejs
                            :main          matthiasn.systems-toolbox.perf-runner
                            :optimizations :advanced}}]})

(defproject otterviews-ui "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2511"]
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;;   UI
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 [com.facebook/react "0.11.2"]
                 [prismatic/om-tools "0.3.10" :exclusions [org.clojure/clojure]]
                 [racehub/om-bootstrap "0.3.2" :exclusions [org.clojure/clojure]]
                 [om "0.7.3"]
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;;  Utils
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;                 
                 [figwheel "0.2.0-SNAPSHOT"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [com.taoensso/tower "3.0.2"]
                 [prismatic/schema "0.3.3"]
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;;  Data
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;                 
                 [datascript "0.7.2"]
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;;  CSS
                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;                                  
                 [garden "1.2.5"]]
  
  :jvm-opts ^:replace ["-Xmx512m" "-server"]
  
  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-garden "0.2.5"]
            [lein-ancient "0.5.5"]
            [lein-figwheel "0.2.0-SNAPSHOT"]]
  
  :source-paths ["src/cljs"]
  
  :garden {:builds [{:id "dev"
                     :source-paths ["src/styles"]
                     :stylesheet otterviews-ui.styles/otterviews
                     :compiler {
                                :output-to "resources/public/css/otterviews_ui.css"
                                :pretty-print? true}}
                    {:id "min"
                     :source-paths ["src/styles"]
                     :stylesheet otterviews-ui.styles/otterviews
                     :compiler {
                                :output-to "dist/otterviews_ui.min.css"
                                :pretty-print? false}}]}
  
  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/cljs" "src-dev/cljs"]
              :compiler {:output-to "resources/public/js/compiled/otterviews_ui.js"
                         :output-dir "resources/public/js/compiled/out"
                         :optimizations :none
                         :source-map true}}
             {:id "min"
              :source-paths ["src/cljs"]
              :compiler {:output-to "dist/otterviews_ui.min.js"
                         :optimizations :advanced
                         :pretty-print false
                         :preamble ["react/react.min.js"]
                         :externs ["react/externs/react.js" "datascript/externs.js"]}}]}
  
  :figwheel {:http-server-root "public"
             :server-port 3449
             :css-dirs ["resources/public/css"]})

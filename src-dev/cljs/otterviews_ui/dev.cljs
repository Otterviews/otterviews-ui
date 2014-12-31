(ns otterviews-ui.dev
  (:require [figwheel.client :as fw]
            [otterviews-ui.core :as c]))

(enable-console-print!)

(fw/watch-and-reload :jsload-callback #(println "reloaded"))
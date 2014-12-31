(ns otterviews-ui.styles
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]))

(defstyles otterviews
  [[:body
   {:font-family "sans-serif"
    :font-size (px 16)
    :line-height 1.5}]])
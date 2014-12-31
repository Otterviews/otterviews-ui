(ns otterviews-ui.core
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponentk]]
            [otterviews-ui.i18n :as i]
            [otterviews-ui.ui :as ui]
            [otterviews-ui.data :as d]
            [otterviews-ui.init :as init]))

(enable-console-print!)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Side Bar
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defcomponentk side-bar
  "Side Bar"
  [data owner]
  (display-name [_] "side-bar")
  (render [_]
          (ui/sidebar [:side-bar] {})))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Navigation Bar
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn ->nav-item [item]
  (ui/nav-item [:navigation-bar :item] {} (i/text item)))

(defcomponentk navigation-bar
  "Navigation Bar"
  [data owner]
  (display-name [_] "navigation-bar")
  (render [_]
          (ui/navbar [:navigation-bar] {}
                     (map ->nav-item (d/get-nav-items)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Search Box
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn change-name [el]
  (d/set-name (.. el -target -value)))

(defcomponentk search-box-input 
  "Simple search box input"
  [data owner]
  (display-name [_] "search-box-input")
  (render [_]
          (let [text (i/text :search-box.input/placeholder)]
            (ui/input [:search-box :input] 
                      {:placeholder text :on-change change-name}))))

(defcomponentk search-box-button 
  "Simple search box button"
  [data owner]
  (display-name [_] "search-box-button")
  (render [_]
          (let [text (i/text :search-box.button/text)]
            (ui/button [:search-box :button] {} text))))

(defcomponentk search-box 
  "Simple search box"
  [data owner]
  (display-name [_] "search-box")
  (render [_]
          (ui/line [:search-box] {} 
                   (->search-box-input data) 
                   (->search-box-button data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  App
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defcomponentk app 
  "Assembled application"
  [data owner]
  (display-name [_] "app")
  (render [_]
          (ui/div [:app] {}
                  (->navigation-bar data)
                  (->side-bar data)
                  (->search-box data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Initialize
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(om/root app d/conn
         {:shared {:conn d/conn}
          :target (. js/document (getElementById "app"))})

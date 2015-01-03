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
;;  Posts
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defcomponentk post 
  "A post"
  [data owner]
  (display-name [_] "post")
  (render [_]
          (ui/div [:posts-container :post] {}
                  (ui/line [:post :title]   {} (first data))
                  (ui/line [:post :content] {} (second data)))))

(defcomponentk posts-container
  "Where you can see posts"
  [data owner]
  (display-name [_] "post-container")
  (render [_]
          (ui/div [:posts-container] {}  
                   (map ->post (d/get-posts)))))

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
                  (->posts-container data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Initialize
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(om/root app d/conn
         {:shared {:conn d/conn}
          :target (. js/document (getElementById "app"))})

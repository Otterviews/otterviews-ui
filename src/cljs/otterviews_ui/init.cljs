(ns otterviews-ui.init
  (:require [om.core :as om :include-macros true]
            [datascript :as d]
            [otterviews-ui.data :as db]))

(enable-console-print!)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Initialize
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce draft-uri "https://otterviews-drafts.firebaseio.com/drafts")

(d/transact! db/conn
             [{:db/id 1
               :i18n/locale :en-US}])

(d/transact! db/conn
             [{:db/id -1
               :navbar/item :navbar.item/home}])

(d/transact! db/conn
             [{:db/id -1
               :navbar/item :navbar.item/about}])

(let [firebase (js/Firebase. draft-uri)]
  (.on firebase 
       "child_added" 
       #(let [get-field (fn [nm] (.val (.child %1 nm)))]
          (d/transact! db/conn
             [{:db/id -1
               :post/title   (get-field "title")
               :post/content (get-field "content")
               :post/date    (get-field "date")}]))))



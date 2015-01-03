(ns otterviews-ui.data
  (:require [om.core :as om :include-macros true]
            [datascript :as d]
            [datascript.core :as dc]))

(enable-console-print!)

(extend-type dc/DB
  om/IToCursor
  (-to-cursor
    ([this _] this)
    ([this _ _] this)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Public
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce conn (d/create-conn {}))

(defn get-name []
  (ffirst (d/q '[:find ?name :where [?e :person/name ?name]] @conn)))

(defn get-locale []
  (ffirst (d/q '[:find ?locale :where [?e :i18n/locale ?locale]] @conn)))

(defn get-nav-items []
  (identity (d/q '[:find ?item :where [?e :navbar/item ?item]] @conn)))

(defn get-posts []
  (identity (d/q '[:find ?title ?content :where 
                   [?e :post/title ?title]
                   [?e :post/content ?content]] @conn)))
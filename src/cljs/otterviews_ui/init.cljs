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

(d/transact! db/conn
             [{:db/id 1
               :person/name "Pedro"}])

(d/transact! db/conn
             [{:db/id 2
               :i18n/locale :en-US}])

(d/transact! db/conn
             [{:db/id -1
               :navbar/item :navbar.item/home}])

(d/transact! db/conn
             [{:db/id -1
               :navbar/item :navbar.item/about}])


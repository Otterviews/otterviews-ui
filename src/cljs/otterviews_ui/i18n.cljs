(ns otterviews-ui.i18n
  (:require-macros [taoensso.tower :as tower-macros :refer (with-tscope)])
  (:require [taoensso.tower :as tower]
            [otterviews-ui.data :as d]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  I18n
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def ^:private tower-config
  {:fallback-locale :en-US
   :compiled-dictionary (tower-macros/dict-compile "dictionary.clj")})

(def ^:private translate (tower/make-t tower-config))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Public
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn text [names]
  (translate (d/get-locale) names))

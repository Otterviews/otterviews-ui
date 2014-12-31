(ns otterviews-ui.ui
  (:require [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.button :as b]
            [om-bootstrap.grid :as g]
            [om-bootstrap.input :as i]
            [om-bootstrap.nav :as n]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Public
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-class
  "Creates a css class acording to the app pattern" 
	([block] (name block))
	([block element] (str (name block) "__" (name element)))
	([block element modifier] (str (name block) "__" (name element) "--" (name modifier))))

(defn merge-attrs [cls attrs]
  (merge {:class (apply create-class cls)} attrs))

(defn line 
  "Creates a line (Bootstrap Row)"
  [cls attrs & xs] 
  (let [cols (map #(g/col {:xs 4} %) xs)]
    (g/row (merge-attrs cls attrs)  cols)))

(defn sidebar
  "Creates a sidebar"
  [cls attrs & xs] 
  (g/col (merge-attrs cls (merge {:sm 2} attrs)) 
         (dom/ul {:class "nav nav-stacked affix"} xs)))

(defn navbar
  "Creates a navbar (Bootstrap Navbar)"
  [cls attrs & xs] 
  (n/navbar {} (n/nav (merge-attrs cls attrs) xs)))

(defn nav-item 
  "Creates a nav-item, mainly to normalize the way ui elements are created"
  [cls attrs & inner] 
  (n/nav-item (merge-attrs cls attrs) inner))

(defn button 
  "Creates a button (Bootstrap Button)"
  [cls attrs & inner] 
  (b/button (merge {:class (str (apply create-class cls) " form-control") :bs-size "small"} attrs) inner))

(defn input 
  "Creates an input (Bootstrap input)"
  [cls attrs & inner] 
  (i/input (merge {:class (apply create-class cls) :type "text"} attrs) inner))

(defn div 
  "Creates a div, mainly to normalize the way ui elements are created"
  [cls attrs & inner] 
  (dom/div (merge-attrs cls attrs) inner))

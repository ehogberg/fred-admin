(ns fred-admin.company
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]))


(defn- company-search [_ _]
  (reify
    om/IRender
    (render [_]
      (dom/h1 "Search bar here"))))

(defn companies-index []
  (om/root
   (fn [app owner]
     (reify
       om/IRender
       (render [_]
         (om/build company-search nil))))
   nil
   {:target (. js/document (getElementById "util"))}))





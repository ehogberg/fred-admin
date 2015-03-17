(ns fred-admin.routing
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))


(defroute companies "/companies" []
  (.log js/console "Entered companies..."))

(defn init-routing []
  (secretary/set-config! :prefix "#")
  (let [h (goog.History.)]
    (goog.events/listen h EventType/NAVIGATE
                        #(secretary/dispatch! (.-token %)))
    (doto h (.setEnabled true))))


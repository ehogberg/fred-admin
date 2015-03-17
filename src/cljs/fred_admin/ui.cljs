(ns fred-admin.ui
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.grid :as g]
            [om-bootstrap.random :as r]
            [om-bootstrap.nav :as n]
            ))

(defn intro-screen [_ owner]
  (reify
    om/IRender
    (render [_]
      (r/jumbotron {}
                   (dom/h2 "FRED Admin")))))

(defn nav-item [item _]
  (reify
    om/IRender
    (render [_]
      (n/nav-item {:href (:href item)} (:label item)))))


(defn navigation [nav _]
  (reify
    om/IRender
    (render [_]
      (n/navbar {:brand (dom/a {:href "#"} (:brand nav))
                 :inverse? true}
                (n/nav {:collapsible? true}
                       (om/build-all nav-item (:menu nav)))))))

(defn index [app _]
  (reify
    om/IRender
    (render [_]
      (dom/div app)
      (g/grid {}
                  (g/row {}
                         (g/col {:md 12}
                                (dom/div {:id "header"}
                                         (om/build navigation (get app :nav)))))
                  (g/row {}
                         (g/col {:md 12}
                                (dom/div {:id "util"} "")))
                  (g/row {}
                         (g/col {:md 12}
                                (dom/div {:id "content"}
                                         (om/build intro-screen nil))))
                  (g/row {}
                         (g/col {:md 12}
                                (dom/div {:id "footer"} "")))))))

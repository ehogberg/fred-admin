(ns fred-admin.core
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.grid :as g]
            [om-bootstrap.random :as r]
            [om-bootstrap.nav :as n]
            [om-tools.core :refer-macros [defcomponent]]))

(defonce app-state (atom {:nav {:brand "Admin"
                                :menu [{:label "Companies"}
                                       {:label "Users"}
                                       {:label "Accounts"}]}}))


(defn intro-screen [_ owner]
  (reify
    om/IRender
    (render [_]
      (r/jumbotron {}
                   (dom/h2 "FRED Admin Home")))))

(defn nav-item [item _]
  (reify
    om/IRender
    (render [_]
      (n/nav-item {:href "#"} (:label item)))))

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

(defn main []
  (om/root
    (fn [app owner]
      (reify
        om/IRender (render [_] (om/build index app))))
    app-state
    {:target (. js/document (getElementById "app"))}))











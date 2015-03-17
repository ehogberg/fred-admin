(ns fred-admin.core
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [fred-admin.ui :refer [index]]))

(defonce app-state (atom {:nav {:brand "Admin"
                                :menu [{:label "Companies"
                                        :href  "#/companies"}
                                       {:label "Users"
                                        :href  "#/users"}
                                       {:label "Accounts"
                                        :href  "#/accounts"}]}}))

(defn main []
  (om/root
    (fn [app owner]
      (reify
        om/IRender
        (render [_]
          (om/build index app))))
    app-state
    {:target (. js/document (getElementById "app"))}))

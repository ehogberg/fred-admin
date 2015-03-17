(ns fred-admin.main
  (:require [fred-admin.core :as core]
            [fred-admin.routing :as routing]))

(routing/init-routing)
(core/main)

(ns fred-admin.core-spec
  (:require-macros [speclj.core :refer [describe it should= run-specs]]
                   [clojure.core.async :refer [go]])
  (:require [speclj.core :as spec]
            [fred-admin.core]
            [om.dom :as dom :include-macros true]))

(describe "A ClojureScript test"
          (it "fail. Fix it!"
              (should= 0 1)))

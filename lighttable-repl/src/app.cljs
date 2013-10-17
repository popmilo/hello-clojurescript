(ns app
    (:require [app.world :as world]
              [goog.events :as goog-events]))

;(js/alert (world/default-string))

(defn drawSquare [x y dx dy]
    (let [target (.getElementById js/document "tunelcanvas")
          context (.getContext target "2d")]
       (.fillRect context x y dx dy)
    ))

(defn ^:export init []
     (drawSquare 0 0 10 10)
  )


(def colors [[0 0 0]
             [255 255 255]
             [104 55 43]
             [112 164 178]
			       [111 61 134]
			       [88 141 67]
			       [53 40 121]
			       [184 199 111]
			       [111 79 37]
			       [67 57 0]
			       [154 103 89]
			       [68 68 68]
			       [108 108 108]
			       [154 210 132]
			       [108 94 181]
			       [149 149 149]])

;(init)

(def screen-size-x 160)
(def screen-size-y 96)
(def middle-screen-x (int (/ screen-size-x 2)))
(def middle-screen-y (int (/ screen-size-y 2)))

(defn index-to-coords [i xsize]
   [(- (int (/ i xsize)) middle-screen-y) (- (rem i xsize) middle-screen-x)])

(defn gen-all-pixels [xsize ysize]
  (range (* xsize ysize)))

(def all-pixels (gen-all-pixels screen-size-x screen-size-y))

(defn gen-all-pixel-coords [pixels xsize]
  (map #(index-to-coords % xsize) pixels))

(def all-pixel-coords (gen-all-pixel-coords all-pixels screen-size-x))

(defn coords-in-circle [coords rmin rmax]
  (let [x (first coords)
        y (second coords)
        r (.sqrt js/Math (+ (* x x) (* y y)))]
  (and (>= r rmin) (< r rmax))))


(defn gen-tunnel-pixel-coords [pixels rmin rmax]
  (filter #(coords-in-circle % rmin rmax) pixels))

(def tunnel-pixel-coords (gen-tunnel-pixel-coords all-pixel-coords 14 48))


(def canvas-surface
  (let [s (.getElementById js/document "tunelcanvas")]
    [(.getContext s "2d")
     (. s -width)
     (. s -height)]))


(defn fill-rect [[surface] [x y width height] [r g b]]
  (set! (. surface -fillStyle) (str "rgb(" r "," g "," b ")"))
  (.fillRect surface x y width height))

(defn draw-pixel [pixel]
  (let [x (* 2 (+ middle-screen-x (first pixel)))
        y (* 2 (+ middle-screen-y (second pixel)))]
    (fill-rect canvas-surface [x y 2 2] [128 128 128])))

(defn ^:export drawtunel []
     (map draw-pixel tunnel-pixel-coords)
  )

(defn ^:export initialise []
  (drawtunel))



(js/alert (world/default-string))
(initialise)
(js/alert "Gotovo crtanje ?")

import React from "https://cdn.skypack.dev/react";
import ReactDOM from "https://cdn.skypack.dev/react-dom";

const skillTags = document.querySelectorAll(".skill-slider-name");

const TAGS = [];

skillTags.forEach((skill) => {
  TAGS.push(skill.textContent);
});

const DURATION = 15000;
const ROWS = 4;
const TAGS_PER_ROW = 20;

const random = (min, max) => Math.floor(Math.random() * (max - min)) + min;
const shuffle = (arr) => [...arr].sort(() => 0.5 - Math.random());

const InfiniteLoopSlider = ({ children, duration, reverse = false }) => {
  return /*#__PURE__*/ React.createElement(
    "div",
    {
      className: "loop-slider",
      style: {
        "--duration": `${duration}ms`,
        "--direction": reverse ? "reverse" : "normal",
      },
    } /*#__PURE__*/,

    React.createElement("div", { className: "inner-skill" }, children, children)
  );
};

const Tag = ({ text } /*#__PURE__*/) =>
  React.createElement(
    "div",
    { className: "tag" },
    /*#__PURE__*/ React.createElement("span", null, "#"),
    " ",
    text
  );

const App = () =>
  /*#__PURE__*/
  React.createElement(
    "div",
    { className: "app-skill" } /*#__PURE__*/,

    React.createElement(
      "div",
      { className: "tag-list" },
      [...new Array(ROWS)].map((_, i /*#__PURE__*/) =>
        React.createElement(
          InfiniteLoopSlider,
          {
            key: i,
            duration: random(DURATION - 5000, DURATION + 5000),
            reverse: i % 2,
          },
          shuffle(TAGS)
            .slice(0, TAGS_PER_ROW)
            .map((tag /*#__PURE__*/) =>
              React.createElement(Tag, { text: tag, key: tag })
            )
        )
      ) /*#__PURE__*/

      //   React.createElement("div", { className: "fade" })
    )
  );
const skillSlid = document.getElementById("skill-slid");
ReactDOM.render(/*#__PURE__*/ React.createElement(App, null), skillSlid);

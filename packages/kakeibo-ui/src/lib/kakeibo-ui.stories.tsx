import { ComponentStory, ComponentMeta } from '@storybook/react';
import { KakeiboUi } from './kakeibo-ui';

const Story: ComponentMeta<typeof KakeiboUi> = {
  component: KakeiboUi,
  title: 'KakeiboUi',
};
export default Story;

const Template: ComponentStory<typeof KakeiboUi> = (args) => (
  <KakeiboUi {...args} />
);

export const Primary = Template.bind({});
Primary.args = {};
